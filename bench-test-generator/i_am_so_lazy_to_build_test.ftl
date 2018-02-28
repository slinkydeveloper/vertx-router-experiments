<#assign val = JsonPath.parse(documents[0].content).read("$")>
package io.slinkydeveloper.bench;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public abstract class BaseRouterTest {
    abstract Router getRouter();

    Router router = getRouter();

<#list val.paths as i>
    @Test
    public void testPath${i?counter}() {
        assertTrue(router.route("${i}"));
    }

</#list>
    @Test
    public void testNotFound1() {
        assertFalse(router.route("/posts/post1"));
    }

}
