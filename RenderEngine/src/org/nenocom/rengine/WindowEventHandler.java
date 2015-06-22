/**
 * 
 */
package org.nenocom.rengine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowIconifyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

/**
 * @author Antonio Ruiz
 *
 */
public abstract class WindowEventHandler {

	private final GLFWKeyCallback keyCallback;
	private final GLFWCursorEnterCallback cursorEnterCallback;
	private final GLFWCursorPosCallback cursorPosCallback;
	private final GLFWMouseButtonCallback mouseButtonCallback;
	private final GLFWScrollCallback scrollCallback;
	private final GLFWWindowFocusCallback windowFocusCallback;
	private final GLFWWindowSizeCallback windowSizeCallback;
	private final GLFWWindowIconifyCallback windowIconifyCallback;
	private final long windowHandle;
	
	public WindowEventHandler(long windowHandle) {
		this.windowHandle = windowHandle;
		keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if(action == GLFW_PRESS){
					onKeyPress(key, scancode, mods);
				}else if(action == GLFW_RELEASE){
					onKeyRelease(key, scancode, mods);
				}else if(action == GLFW_REPEAT){
					onKeyRepeat(key, scancode, mods);
				}
			}
		};
		glfwSetKeyCallback(windowHandle, keyCallback);

		cursorEnterCallback = new GLFWCursorEnterCallback() {
			@Override
			public void invoke(long window, int entered) {
				if(entered == GL_TRUE){
					onMouseEnter();
				}else{
					onMouseExit();
				}
			}
		};
		glfwSetCursorEnterCallback(windowHandle, cursorEnterCallback);

		cursorPosCallback = new GLFWCursorPosCallback() {
			@Override
			public void invoke(long window, double xpos, double ypos) {
				onMouseMove(xpos, ypos);
			}
		};
		glfwSetCursorPosCallback(windowHandle, cursorPosCallback);

		mouseButtonCallback = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				if(action == GLFW_PRESS){
					onMouseClick(button, mods);
				}else if(action == GLFW_RELEASE){
					onMouseRelease(button, mods);
				}
			}
		};
		glfwSetMouseButtonCallback(windowHandle, mouseButtonCallback);

		scrollCallback = new GLFWScrollCallback() {
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				if(yoffset != 0){
					onMouseWheelMove(yoffset);
				}
			}
		};
		glfwSetScrollCallback(windowHandle, scrollCallback);

		windowFocusCallback = new GLFWWindowFocusCallback() {
			@Override
			public void invoke(long window, int focused) {
				if(focused == GL_TRUE){
					onWindowFocus();
				}else{
					onWindowLostFocus();
				}
			}
		};
		glfwSetWindowFocusCallback(windowHandle, windowFocusCallback);
		
		windowSizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				onSizeChanged(width, height);
			}
		};
		glfwSetWindowSizeCallback(windowHandle, windowSizeCallback);
		
		windowIconifyCallback = new GLFWWindowIconifyCallback() {
			@Override
			public void invoke(long window, int iconified) {
				if(iconified == GL_TRUE){
					onWindowMinimize();
				}else{
					onWindowRestore();
				}
			}
		};
		glfwSetWindowIconifyCallback(windowHandle, windowIconifyCallback);
	}
	
	public abstract void onMouseEnter();

	public abstract void onMouseExit();

	public abstract void onKeyRelease(int key, int scancode, int mods);

	public abstract void onKeyPress(int key, int scancode, int mods);

	public abstract void onMouseMove(double xpos, double ypos);

	public abstract void onKeyRepeat(int key, int scancode, int mods);

	public abstract void onMouseClick(int button, int mods);

	public abstract void onMouseRelease(int button, int mods);

	public abstract void onMouseWheelMove(double yoffset);

	public abstract void onWindowLostFocus();

	public abstract void onSizeChanged(int width, int height);

	public abstract void onWindowMinimize();

	public abstract void onWindowRestore();

	public abstract void onWindowFocus();

	public void release(){
		keyCallback.release();
		cursorEnterCallback.release();
		cursorPosCallback.release();
		mouseButtonCallback.release();
		scrollCallback.release();
		windowFocusCallback.release();
		windowSizeCallback.release();
		windowIconifyCallback.release();
	}

	/**
	 * @return the windowHandle
	 */
	public long getWindowHandle() {
		return windowHandle;
	}
}
