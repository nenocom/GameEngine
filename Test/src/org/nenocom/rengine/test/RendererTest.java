/**
 * 
 */
package org.nenocom.rengine.test;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.nenocom.rengine.BasicWindow;
import org.nenocom.rengine.Renderer;
import org.nenocom.rengine.event.WindowEventAdapter;

/**
 * @author Antonio Ruiz
 *
 */
public class RendererTest implements Renderer{
	
	private int i;

	@Override
	public void init() {
		System.out.println("Hello.");
		glClearColor(0, 0.5f, 0.5f, 1);
	}
	
	@Override
	public void render() {
		System.out.println("Rendering frame: "+i++);
		GL11.glClear(GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void release() {
		System.out.println("Bye.");
	}

	
	public static void main(String[] args){
		BasicWindow basicWindow = new BasicWindow(){

			
			@Override
			protected void initRenderer() {
				renderer = new RendererTest();
			}

			
			@Override
			public void initEventHandler() {
				eventHandler = new WindowEventAdapter(getWindowHandle()){

					@Override
					public void onKeyPress(int key, int scancode, int mods) {
						if(key == GLFW_KEY_ESCAPE){
							glfwSetWindowShouldClose(getWindowHandle(), GL_TRUE);
						}
					}
					
				};
			}
			
		};
		basicWindow.init();
	}
}
