/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package ntt.demo;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import se.ericsson.ntt.framework.configuration.Configuration;
import se.ericsson.ntt.framework.configuration.NttConfiguration;
import se.ericsson.ntt.framework.interaction.PluginInteraction;
import se.ericsson.ntt.framework.internal.core.Ntt;
import se.ericsson.ntt.framework.plugin.PluginConfiguration;
import se.ericsson.ntt.ft.testcase.Ntt2;
import se.ericsson.ntt.handler.logging.MultiLoggingHandler;
import se.ericsson.ntt.plugin.httpclient.NttHttpClientManager;
import se.ericsson.ntt.plugin.httpclient.message.request.GetRequest;
import se.ericsson.ntt.plugin.httpclient.message.response.GetResponse;

public class DemoHttpClientPlugin {

    @Test
    public void demo() {
        //init log4j
        BasicConfigurator.configure();

        //new Ntt        
        Ntt.getInstance();

        //build pluginConfiguration
        PluginConfiguration httpClientPluginConfiguration = getHttpClientPluginConfiguration();

        //init Ntt
        Ntt2 ntt2 = new Ntt2(new PluginConfiguration[] { httpClientPluginConfiguration });

        //init PluginInteration
        //Map<String, PluginInteraction> pluginInteractions = new HashMap<String, PluginInteraction>();
        PluginInteraction httpPluginInteraction = ntt2.getPluginInteraction(httpClientPluginConfiguration);
        //pluginInteractions.put(httpClientPluginConfiguration.getConfigurationName(), httpPluginInteraction);

        //logging setting
        MultiLoggingHandler.startTestCase(getClass().getName());
        MultiLoggingHandler.startGroup("demo");

        //Sent out the http Request
        httpPluginInteraction.send(getArbitaryHttpGetReq());

        //Expected to receive 200 response
        httpPluginInteraction.receive(getHttpGet200Response());

        //loging end
        MultiLoggingHandler.endGroup();
        MultiLoggingHandler.endTestCase();

    }

    private GetRequest getArbitaryHttpGetReq() {
        GetRequest getRequest = new GetRequest();
        getRequest.setUri("");
        return getRequest;
    }

    private GetResponse getHttpGet200Response() {
        GetResponse getResponse = new GetResponse();
        getResponse.setResponseCode(200);
        return getResponse;
    }

    private PluginConfiguration getHttpClientPluginConfiguration() {

        return new PluginConfiguration() {

            public Class getPluginInterfaceClass() {
                //NttHttpClientManager 拥有NttHttpClient的 实现
                return NttHttpClientManager.class;
            }

            public String getConfigurationName() {
                // TODO Auto-generated method stub
                return "client";
            }

            public void configure(NttConfiguration nttConfiguration, Configuration configuration) {

                //将pluginConfiguration的配置信息传递到Ntt Plugin
                configuration.set(NttHttpClientManager.remote_host, "localhost");
                configuration.set(NttHttpClientManager.remote_port, "8080");
                configuration.set(NttHttpClientManager.response_body_encoding, "UTF-8");

            }
        };
    }

}
