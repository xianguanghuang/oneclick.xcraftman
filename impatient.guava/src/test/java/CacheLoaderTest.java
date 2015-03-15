import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import impatient.guava.Pojo;
import impatient.guava.PojoFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

/**
 * Created by xianguanghuang on 15-3-15.
 */
public class CacheLoaderTest {


    @Before
    public void before(){
        PojoFactory.clearCounter();
    }

    @Test
    public void testPopulateUsingLoadingCache(){

        LoadingCache<String, Pojo> loadingCache = CacheBuilder.newBuilder()
                                                    .maximumSize(100)
                                                    .build(new CacheLoader<String, Pojo>() {
                                                        @Override
                                                        public Pojo load(String s) throws Exception {
                                                            return PojoFactory.buildPojo();
                                                        }
                                                    });

        try {
            Pojo pojo = loadingCache.get("abc");
            assertEquals(0, pojo.getNumber());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Pojo pojo = loadingCache.getUnchecked("abc");
        assertEquals(0, pojo.getNumber());

    }

    @Test
    public void testPopulateUsingCache() throws ExecutionException {

        Cache cache = CacheBuilder.newBuilder().maximumSize(100).build();
        cache.get("abc", new Callable<Pojo>(){

            @Override
            public Pojo call() throws Exception {
                return PojoFactory.buildPojo();
            }
        });

        Pojo pojo = (Pojo) cache.getIfPresent("abc");
        assertEquals(0, pojo.getNumber());

    }

}
