/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.artemis;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.artemis.component.PackedPosition;
import com.artemis.component.StructComponentA;
import com.artemis.system.EntityDeleterSystem;
import com.artemis.system.PackedPositionSystem;
import com.artemis.system.PackedPositionSystem2;
import com.artemis.system.PackedPositionSystem3;
import com.github.esfbench.JmhSettings;

public class LegacyPackedComponentBenchmark extends JmhSettings {
	
	private World worldPacked;
	
//	@Setup
	public void init() {
		worldPacked = new World(new WorldConfiguration()
				.setSystem(new PackedPositionSystem())
				.setSystem(new PackedPositionSystem2())
				.setSystem(new PackedPositionSystem3())
				.setSystem(new EntityDeleterSystem(
						JmhSettings.SEED, entityCount, PackedPosition.class, StructComponentA.class)));
	}
	
//	@Benchmark
	public void packed_legacy() {
		worldPacked.process();
	}

	public static void main(String[] args) throws Exception {
		new Runner(
			new OptionsBuilder()
				.include(LegacyPackedComponentBenchmark.class.getName() + ".*")
				.param("entityCount", "1024", "4096")
				.build())
		.run();
	}
}
