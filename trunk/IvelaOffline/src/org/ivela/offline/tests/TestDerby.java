package org.ivela.offline.tests;

import static org.junit.Assert.*;

import org.ivela.offline.utils.Utils;
import org.junit.Test;

public class TestDerby {

	@Test
	public void test() {
		System.out.println(Utils.getInstance().getLocalAppData());
		System.out.println(Utils.getInstance().getRealExecPath("/Internet Explorer/iexplore.exe"));
	}

}
