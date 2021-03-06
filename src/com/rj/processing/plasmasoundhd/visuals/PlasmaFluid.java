package com.rj.processing.plasmasoundhd.visuals;

import msafluid.MSAFluidSolver2D;
import processing.core.PApplet;
import processing.core.PImage;
import android.util.Log;
import android.view.MotionEvent;

import com.rj.processing.plasmasoundhd.PDActivity;

public class PlasmaFluid extends Visual {
	final float [] FLUID_QUALITIES = {0, 40, 90};
	int currentQuality = 1;

	public MSAFluidSolver2D fluidSolver;

	PImage imgFluid;
	PDActivity pp;

	public PlasmaFluid(final PApplet p, PDActivity pp) {
		super(p);
		this.pp = pp;
		initVis();
	}
	
	private void setupFluid() {
		if (pp.inst == null) {
			currentQuality = -1;
			return;
		}
		currentQuality = pp.inst.visualQuality % FLUID_QUALITIES.length;
		float FLUID_WIDTH = FLUID_QUALITIES[currentQuality];
	    // create fluid and set options
	    fluidSolver = new MSAFluidSolver2D((int)(FLUID_WIDTH), (int)(FLUID_WIDTH * height/width));
	    
	    //options
		fluidSolver.enableRGB(true).setFadeSpeed(0.01f).setDeltaT(0.5f).setVisc(0.0001f).setSolverIterations(3);
	    
	    // create image to hold fluid picture
	    imgFluid = p.createImage(fluidSolver.getWidth(), fluidSolver.getHeight(), p.ARGB);
	}

	public void initVis() {
		setupFluid();
		
		// textMode(SHAPE);
	}

	
	public void drawVis() {
		if (pp.inst == null || pp.inst.visualQuality % FLUID_QUALITIES.length != currentQuality) {
			setupFluid();
		}
		p.pushStyle();
		
		p.textMode(p.MODEL);
		p.rectMode(p.CENTER);
		p.ellipseMode(p.CENTER);
		drawFluid();
		
		
		p.popStyle();
		
		
	}

	private void drawFluid() {
		if (currentQuality <= 0 || fluidSolver == null) return;
	    fluidSolver.update();
	    
	    imgFluid.loadPixels();
	    final int cellcount = fluidSolver.getNumCells();
	    for(int i=0; i<cellcount; i++) { //optimize here.
	        imgFluid.pixels[i] = p.color(fluidSolver.r[i], fluidSolver.g[i], fluidSolver.b[i]);
	    }  
	    imgFluid.updatePixels();//  fastblur(imgFluid, 2);
	    
	    p.image(imgFluid, 0, 0, width, height);
	}
	
	
	
	public void touchEvent(final MotionEvent me, final int i, final float x, final float y, final float vx,
			final float vy, final float size) {
		fluidTouchEvent(me,i,x,y,vx,vy,size);
	}

	private void fluidTouchEvent(final MotionEvent me, final int i, final float x, final float y, float vx,
			float vy, final float size) {
		
		final float velocityScale = 50f;
		
		//PApplet.println(""+vx+","+vy);
		vx = vx * velocityScale;
		vy = vy * velocityScale;
		
	    addForce(x/width, y/height, vx/width, vy/height);
	}
	
	// add force and dye to fluid, and create particles
	public void addForce(final float x, final float y, float dx, float dy) {
		if (fluidSolver == null) return;
	        float colorMult = 5;
	        colorMult=colorMult*y;
	        final float velocityMult = 30.0f;

	        if (dx > 1) dx = 1;
	        if (dy > 5) dy = 1;

	        int drawColor;

	        p.colorMode(p.HSB, 360, 1, 1);
	        final float hue = ((x + y) * 180 + p.frameCount) % 360;
	        drawColor = p.color(hue, 1, 1);
	        p.colorMode(PApplet.RGB, 255);  
	        for (int i=0; i<3; i++) {
	        	for (int j=0; j<1; j++) {
		        	final int index = fluidSolver.getIndexForNormalizedPosition(x+.01f*i, y+.01f*j);
			        fluidSolver.rOld[index]  += p.red(drawColor) * colorMult;
			        fluidSolver.gOld[index]  += p.green(drawColor) * colorMult;
			        fluidSolver.bOld[index]  += p.blue(drawColor) * colorMult;
			
			        fluidSolver.uOld[index] += dx * velocityMult;
			        fluidSolver.vOld[index] += dy * velocityMult;
	        	}
	        }
	}




}
