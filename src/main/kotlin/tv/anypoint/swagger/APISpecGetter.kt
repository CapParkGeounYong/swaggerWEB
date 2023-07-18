package tv.anypoint.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.Resource
import springfox.documentation.swagger.web.SwaggerResource
import springfox.documentation.swagger.web.SwaggerResourcesProvider


@Configuration
class APISpecGetter {

    @Primary
    @Bean
    fun fetchSwaggerResources(): SwaggerResourcesProvider {
        //수정 > 인터페이스로 의존성 주입
        val apiDocumentPathGetter= ApiDocumentPathGetter()
        val apiDocumentPaths=apiDocumentPathGetter.getPath()

        val swaggerResources: MutableList<SwaggerResource> = ArrayList()
        for (apiDocumentPath in apiDocumentPaths){
            swaggerResources.add(fetchSwaggerResource(apiDocumentPath))
        }
        return SwaggerResourcesProvider{swaggerResources}
    }

    fun fetchSwaggerResource(resource: Resource): SwaggerResource {
        val swaggerResource = SwaggerResource()
        swaggerResource.setName(
            resource
                .filename
                ?.replace(".yaml", ""))
        swaggerResource.setSwaggerVersion("3.0")
        swaggerResource.setLocation(//setlocation 가능하니 그냥 yaml 보낼 때 경로 지정해주면 된다.
            resource
                .file
                .toString()
                .split("static")[1]
            /**
             *  static 앞의 경로 삭제하기 위해 사용 >
             *  예: /Users/name/Desktop/untitled/target/classes/static/swagger-apis/api1/swagger.yaml
             *  > /swagger-apis/api1/swagger.yaml
             * */
        )
        return swaggerResource
    }
}

