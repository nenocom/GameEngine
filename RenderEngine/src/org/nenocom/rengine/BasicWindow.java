package org.nenocom.rengine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;



import org.nenocom.rengine.event.WindowEventHandler;
import org.lwjgl.opengl.GLContext;

public class BasicWindow implements Window{

	private static final String DEFAULT_TITLE = "Basic Window.";
	private static final int DEFAULT_HEIGHT = 480;
	private static final int DEFAULT_WIDTH = 640;
	
	private long windowHandler;
	private Thread renderThread;
	protected WindowEventHandler eventHandler;
	
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
					
					initEventHandler();
					
					while (glfwWindowShouldClose(windowHandler) == GL_FALSE) {
						//TODO: render
						glfwSwapBuffers(windowHandler);
						glfwPollEvents();
					}
					
					if(eventHandler != null){
						eventHandler.release();
					}
					
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

	@Override
	public void close() {
		glfwSetWindowShouldClose(windowHandler, GL_TRUE);
	}

	protected void initEventHandler() {
		
	}

}
