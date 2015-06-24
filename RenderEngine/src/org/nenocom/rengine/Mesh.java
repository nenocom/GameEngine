package org.nenocom.rengine;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh implements Renderer{

	private int vertexArrayLocation;
	private int vertexBufferLocation;
	private int verticeCount;

	public Mesh(){
		init();
	}

	public void setVertexData(float[] data){
		verticeCount = data.length/3;
		glBindVertexArray(vertexArrayLocation);
		if(vertexBufferLocation == 0){
			vertexBufferLocation = glGenBuffers();
		}
		glBindBuffer(GL_ARRAY_BUFFER, vertexBufferLocation);
		final FloatBuffer verticeBuffer = ByteBuffer
				.allocateDirect(data.length * Float.SIZE)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		verticeBuffer.put(data);
		verticeBuffer.flip();
		glBufferData(GL_ARRAY_BUFFER, verticeBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	@Override
	public void init() {
		vertexArrayLocation = glGenVertexArrays();
	}

	public void bind(){
		glBindVertexArray(vertexArrayLocation);
	}

	@Override
	public void render() {
		glDrawArrays(GL_TRIANGLES, 0, verticeCount);
	}

	@Override
	public void release() {
		glDeleteBuffers(vertexBufferLocation);
		glDeleteVertexArrays(vertexArrayLocation);
	}



}
