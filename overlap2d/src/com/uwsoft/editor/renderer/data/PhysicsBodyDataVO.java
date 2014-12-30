package com.uwsoft.editor.renderer.data;

import com.badlogic.gdx.math.Vector2;

public class PhysicsBodyDataVO {

    public int bodyType = 0;

	public float mass;
	public Vector2 centerOfMass;
	public float rotationalInertia;
	public float damping;
	public float gravityScale;
	public boolean allowSleep;
	public boolean awake;
	public boolean bullet;

    public float density;
    public float friction;
    public float restitution;
    
    public PhysicsBodyDataVO(){
    	centerOfMass = new Vector2();
    }
    
    public PhysicsBodyDataVO(PhysicsBodyDataVO vo){
    	bodyType = vo.bodyType;
    	mass = vo.mass;
    	centerOfMass = vo.centerOfMass.cpy();
    	rotationalInertia = vo.rotationalInertia;
    	damping = vo.damping;
    	gravityScale = vo.gravityScale;
    	allowSleep = vo.allowSleep;
    	awake = vo.awake;
    	bullet = vo.bullet;
        density = vo.density;
        friction = vo.friction;
        restitution = vo.restitution;
    }
}
