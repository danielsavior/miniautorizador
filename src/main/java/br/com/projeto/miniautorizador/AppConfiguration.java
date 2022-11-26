package br.com.projeto.miniautorizador;

import br.com.projeto.miniautorizador.service.validators.ValidadorDeTransacao;
import br.com.projeto.miniautorizador.service.validators.ValidadorDeTransacaoBean;
import br.com.projeto.miniautorizador.service.validators.ValidadorDeTransacaoExternoBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppConfiguration {

    @ConditionalOnProperty(value = "application.validador-externo", havingValue = "false", matchIfMissing = true)
    @Bean(name = "validadorDeTransacao")
    public ValidadorDeTransacao validadorDeTransacaoDefault(){
        return new ValidadorDeTransacaoBean();
    }

    @ConditionalOnProperty(value = "application.validador-externo", havingValue = "true")
    @Bean(name = "validadorDeTransacao")
    public ValidadorDeTransacao validadorDeTransacaoExterno(){
        return new ValidadorDeTransacaoExternoBean();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("br.com.projeto.miniautorizador.entity");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://mysql:3306/miniautorizador?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    final Properties additionalProperties() {
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "create");

        return props;
    }

}
