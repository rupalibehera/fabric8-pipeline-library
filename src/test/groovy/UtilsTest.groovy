package test.groovy
import static org.assertj.core.api.Assertions.assertThat

import java.nio.charset.Charset

import org.assertj.core.util.Files
import io.fabric8.Utils
import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test


/**
 * Created by rbehera on 10/13/17.
 */
class UtilsTest extends BasePipelineTest{
    @Override
    @Before
    void setUp() throws Exception{
        scriptRoots += 'src/io/fabric8'
        super.setUp()
    }

//    @Test
//    void testGetLatestVersionFromTag() throws Exception {
//        print("hello")
//        //Utils.getLatestVersionFromTag()
//        Script commons = runScript("Utils.groovy")
//        //commons.getLatestVersionFromTag()
//        printCallStack()
//    }

    @Test
    void should_return_name_with_uppercase_underscore() throws Exception {
        Script commons = runScript("Utils.groovy")
        assertThat(commons.cleanName('some name')).isEqualTo("SOME_NAME")
        assertThat(helper.methodCallCount("cleanName")).isEqualTo(1)
//        printCallStack()
    }

}
