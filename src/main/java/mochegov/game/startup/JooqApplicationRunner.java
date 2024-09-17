package mochegov.game.startup;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JooqApplicationRunner implements ApplicationRunner {

    @Value("${spring.datasource.driverClassName}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${jooq.generator.database.name}")
    private String databaseName;

    @Value("${jooq.generator.database.with-includes}")
    private String databaseWithIncludes;

    @Value("${jooq.generator.database.with-excludes}")
    private String databaseWithExcludes;

    @Value("${jooq.generator.database.with-input-schema}")
    private String databaseWithInputSchema;

    @Value("${jooq.generator.target.package-name}")
    private String targetPackageName;

    @Value("${jooq.generator.target.directory}")
    private String targetDirectory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        GenerationTool generationTool = new GenerationTool();
        generationTool.run(configureJooqGenerator());
    }

    private Configuration configureJooqGenerator() {
        return new Configuration()
            .withJdbc(new Jdbc()
                .withDriver(driver)
                .withUrl(url)
                .withUser(username)
                .withPassword(password))
            .withGenerator(new Generator()
                .withDatabase(new Database()
                    .withName(databaseName)
                    .withIncludes(databaseWithIncludes)
                    .withExcludes(databaseWithExcludes)
                    .withInputSchema(databaseWithInputSchema))
                .withTarget(new Target()
                    .withPackageName(targetPackageName)
                    .withDirectory(targetDirectory)));
    }
}