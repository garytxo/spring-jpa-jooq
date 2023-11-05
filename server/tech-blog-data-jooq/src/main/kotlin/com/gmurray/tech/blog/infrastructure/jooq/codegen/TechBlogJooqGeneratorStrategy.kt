package com.gmurray.tech.blog.infrastructure.jooq.codegen


import org.jooq.codegen.DefaultGeneratorStrategy
import org.jooq.codegen.GeneratorStrategy
import org.jooq.meta.Definition
import org.slf4j.LoggerFactory

/**
 * This  customized generator strategy  ensures that each table record implements this interface TechBlogAuditableAwareRecord
 * meaning a customized org.jooq.RecordListener can be created to handle populating tha auditable fields
 */
class TechBlogJooqGeneratorStrategy : DefaultGeneratorStrategy() {

    val log = LoggerFactory.getLogger(TechBlogJooqGeneratorStrategy::class.java)

    companion object {

        //Define certain tables that should be ignord as auditable records
        val EXCLUDED_AUDITABLE_TABLES = setOf("tech_blog.post_post_category")
    }

    override fun getJavaClassImplements(definition: Definition, mode: GeneratorStrategy.Mode): MutableList<String> {


        if (mode == GeneratorStrategy.Mode.RECORD) {

            return if(EXCLUDED_AUDITABLE_TABLES.contains(definition.qualifiedName)){
                log.info("SKIPPING  table:${definition.qualifiedName} as TechBlogAuditableAwareRecord")
                mutableListOf()
            }else{
                log.info("MARK table:${definition.qualifiedName} as TechBlogAuditableAwareRecord")
                mutableListOf(TechBlogAuditableAwareRecord::class.java.name)
            }
        }

        return mutableListOf()
    }
}