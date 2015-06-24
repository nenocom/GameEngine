/**
 * 
 */
package org.nenocom.rengine.test;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.nenocom.rengine.BasicWindow;
import org.nenocom.rengine.Mesh;
import org.nenocom.rengine.Renderer;
import org.nenocom.rengine.event.WindowEventAdapter;

/**
 * @author Antonio Ruiz
 *
 */
public class TriangleTest implements Renderer{
	
	private Mesh triangle;
	private Mesh triangle2;

	@Override
	public void init() {
		glClearColor(0, 0.5f, 0.5f, 1);
		triangle = new Mesh();
		triangle2 = new Mesh();
		float[] triangleVertices = new float[]{
				-0.5f,0.5f,0,
				0.5f,0.5f,0,
				0.5f,-0.5f,0
				};
		float[] triangleVertices2 = new float[]{
				-0.9f,0.5f,0,
				-0.5f,-0.5f,0,
				-0.5f,-1.0f,0
				};
		
		triangle.setVertexData(triangleVertices);
		triangle2.setVertexData(triangleVertices2);
		
	}
	
	@Override
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		triangle.bind();
		triangle.render();
		triangle2.bind();
		triangle2.render();
		
	}

	@Override
	public void release() {
		triangle.release();
	}

	
	public static void main(String[] args){
		BasicWindow basicWindow = new BasicWindow(){

			
			@Override
			protected void initRenderer() {
				renderer = new TriangleTest();
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
