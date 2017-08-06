package cn.yinguowei.boot.configration;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

/**
 * 创建 by 殷国伟 于 2017/8/6.
 */
//@Configuration
public class TomcatSSLConfig {
    @Value("${keystore.file}")
    private String keystoreFile;
    @Value("${keystore.pass}")
    private String keystorePass;

//    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() throws FileNotFoundException {
        final String absoluteKeystoreFile = TomcatSSLConfig.class.getResource("/").getPath() + keystoreFile;// ResourceUtils.getFile(keystoreFile).getAbsolutePath();

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer factory) {
                System.out.println("absoluteKeystoreFile = " + absoluteKeystoreFile);
                if (factory instanceof TomcatEmbeddedServletContainerFactory) {
                    TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
                    containerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
                        @Override
                        public void customize(Connector connector) {
                            connector.setPort(8443);
                            connector.setSecure(true);
                            connector.setScheme("https");
                            Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
                            proto.setSSLEnabled(true);
                            proto.setKeystoreFile(absoluteKeystoreFile);
                            proto.setKeystorePass(keystorePass);
                            proto.setKeystoreType("PKCS12");
                            proto.setKeyAlias("tomcat");
                        }
                    });
                }
            }
        };
    }
}
