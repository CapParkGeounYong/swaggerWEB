package tv.anypoint.swagger

import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternResolver

class ApiDocumentPathGetter {

    fun getPath(): Array<Resource> {
        val resourcePatternResolver: ResourcePatternResolver = PathMatchingResourcePatternResolver()
        /**
         * resources에 필요한 파일 모두 가져오고
         * 메인에 해당하는 것만 추가해주면 된다.
         * 파일 폴더 구조 필요
         * */
        val apiSpecPath: String = "classpath:static/*/main/*.yaml" // 와일드카드(*) 사용 가능
        return resourcePatternResolver.getResources(apiSpecPath)
    }
}