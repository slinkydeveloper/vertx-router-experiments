<#assign val = JsonPath.parse(documents[0].content).read("$") + JsonPath.parse(documents[1].content).read("$")>
package io.slinkydeveloper.bench;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Francesco Guardiani @slinkydeveloper
 */
public class ${val.routerType}Test {

    Router router = new ${val.routerType}().initializeForBench();

<#list val.routes as i>
    @Test
    public void testPath${i?counter}() {
        Optional<String> result = router.route("${i}");
        assertTrue(result.isPresent());
    }

</#list>

}
