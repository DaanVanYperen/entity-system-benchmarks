package com.artemis.system.iterating;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.component.Comp6;
import com.artemis.component.Comp7;
import com.artemis.component.Comp8;
import com.artemis.component.Comp9;
import com.artemis.systems.IteratingSystem;
import org.openjdk.jmh.infra.Blackhole;

public class CompSystemD extends IteratingSystem {
	
	Blackhole voidness = new Blackhole();
	
	@SuppressWarnings("unchecked")
	public CompSystemD() {
		super(Aspect.all(Comp6.class, Comp7.class, Comp8.class).exclude(Comp9.class));
	}

	@Override
	protected void process(int e) {
		voidness.consume(e);
	}
}
