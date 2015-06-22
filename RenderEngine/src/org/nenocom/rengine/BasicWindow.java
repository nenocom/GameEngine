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
	protected Renderer renderer;

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
					initRenderer();
					
					while (glfwWindowShouldClose(windowHandler) == GL_FALSE) {
						renderer.render();
						glfwSwapBuffers(windowHandler);
						glfwPollEvents();
					}
					
					if(eventHandler != null){
						eventHandler.release();
					}
					
					if(renderer != null){
						renderer.release();
					}
					
					glfwDestroyWindow(windowHandler);
					glfwTerminate();
				}
			});
			renderThread.start();
		}
	}

	protected void initRenderer() {
		renderer = new Renderer(){

			@Override
			public void render() {}

			@Override
			public void release() {}
		};
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
