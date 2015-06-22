/**
 * 
 */
package org.nenocom.rengine.test;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.nenocom.rengine.BasicWindow;
import org.nenocom.rengine.event.WindowEventAdapter;

/**
 * @author Antonio Ruiz
 *
 */
public class WindowEventAdapterTest {

	public static void main(String[] args){
		BasicWindow basicWindow = new BasicWindow(){

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
