/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package me.exerosis.builditbigger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiTransformer;

import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.jokes.JokeFactory;
import me.exerosis.builditbigger.jokes.JokeStore;
import rx.Observable;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "builditbigger.exerosis.me",
                ownerName = "builditbigger.exerosis.me",
                packagePath = ""
        )
)
//<servlet>
//<servlet-name>SystemServiceServlet</servlet-name>
//<servlet-class>com.google.api.server.spi.SystemServiceServlet</servlet-class>
//<init-param>
//<param-name>services</param-name>
//<param-value>me.exerosis.builditbigger.JokeEndpoint</param-value>
//</init-param>
//</servlet>
//<servlet-mapping>
//<servlet-name>SystemServiceServlet</servlet-name>
//<url-pattern>/_ah/spi/*</url-pattern>
//    </servlet-mapping>
//
//    <welcome-file-list>
//        <welcome-file>index.html</welcome-file>
//    </welcome-file-list>
public class JokeEndpoint {

//    @Override
    @ApiMethod(name = "getJoke")
  //  @ApiTransformer(JokeTransformer.class)
    public Joke getJoke() {
        return JokeFactory.getInstance().getJoke().toBlocking().first();
    }
}