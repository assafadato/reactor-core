/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package reactor.core.publisher;

import java.util.Objects;
import javax.annotation.Nullable;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.Fuseable;
import reactor.core.Scannable;
import reactor.util.context.Context;

/**
 * @author Stephane Maldini
 */
final class MonoSourceFuseable<I> extends Mono<I> implements Fuseable, Scannable {

	final Publisher<? extends I> source;

	MonoSourceFuseable(Publisher<? extends I> source) {
		this.source = Objects.requireNonNull(source);
	}

	/**
	 * Default is simply delegating and decorating with {@link Mono} API. Note this
	 * assumes an identity between input and output types.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void subscribe(Subscriber<? super I> s, Context context) {
		source.subscribe(s);
	}

	@Override
	@Nullable
	public Object scanUnsafe(Attr key) {
		if (key == ScannableAttr.PARENT) {
			return source;
		}
		return null;
	}
}
