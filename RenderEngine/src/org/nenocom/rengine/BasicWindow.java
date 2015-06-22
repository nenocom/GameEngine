package org.nenocom.rengine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.nenocom.commons.Window;


import org.lwjgl.opengl.GLContext;

public class BasicWindow implements Window{

	private static final String DEFAULT_TITLE = "Basic Window.";
	private static final int DEFAULT_HEIGHT = 480;
	private static final int DEFAULT_WIDTH = 640;
	
	private long windowHandler;
	private Thread renderThread;

	public BasicWindow(){
		
	}

	@Override
	public void init() {
		if(renderThread == null){
			renderThread = new Thread(new Runnable() {

				@Override
				public void run() {
					glfwInit();
					windowHandler = glfwCreateWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TITLE, NULL, NULL);
					glfwMakeContextCurrent(windowHandler);
					GLContext.createFromCurrent();
					glfwSwapInterval(1);
					
					//TODO: set Event Handlers
					
					while (glfwWindowShouldClose(windowHandler) == GL_FALSE) {
						//TODO: render
						glfwSwapBuffers(windowHandler);
						glfwPollEvents();
					}
					
					//TODO: release Event Handlers
					
					glfwDestroyWindow(windowHandler);
					glfwTerminate();
				}
			});
			renderThread.start();
		}
	}

	/**
	 * @return the windowHandle
	 */
	@Override
	public long getWindowHandle() {
		return windowHandler;
	}

}
