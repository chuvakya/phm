package org.zs.phm3.repository.ts.ext;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataUtil {

	public static <T> List<T> toList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	    	r.add(clazz.cast(o));
	    return r;
	}
}
