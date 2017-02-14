package org.wso2.carbon.sample;

import com.google.gson.Gson;
import javafx.util.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static java.net.HttpURLConnection.HTTP_CREATED;

public class SCIMBulkCaller extends AbstractMediator {
    private static final String SCIM_BULK_URL = "https://localhost:9443/wso2/scim/Bulk";
    private static final String SCIM_GROUPS_URL = "https://localhost:9443/wso2/scim/Groups";
    private static final String SCIM_USERS_URL = "https://localhost:9443/wso2/scim/Users";

    private static final Log log = LogFactory.getLog(SCIMBulkCaller.class);

    @Override
    public boolean mediate(MessageContext messageContext) {
        List<Pair<String, String>> roleNameIds = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            Pair<String, String> roleNameId = addUniqueRole();

            if (roleNameId != null) {
                roleNameIds.add(roleNameId);
            }
        }

        Pair<String, String> userNameId = addUniqueUser();

        assignRolesToUser(userNameId, roleNameIds);

        return true;
    }


    private void assignRolesToUser(Pair<String, String> userNameId, List<Pair<String, String>> roleNameIds) {
        Gson gson = new Gson();

        for (Pair<String, String> roleNameId : roleNameIds) {
            SingleRole singleRole = new SingleRole();
            singleRole.setDisplayName("PRIMARY/" + roleNameId.getKey());

            UserMember userMember = new UserMember();
            userMember.setValue(userNameId.getValue());
            userMember.setDisplay(userNameId.getKey());

            singleRole.addMember(userMember);

            String json = gson.toJson(singleRole);

            CloseableHttpClient client = getHttpClient();

            if (client != null) {

                HttpPatch httpPatch = new HttpPatch(SCIM_GROUPS_URL + "/" + roleNameId.getValue());

                try {
                    setHeadersAndBody(json, httpPatch);

                    CloseableHttpResponse response = client.execute(httpPatch);
                    String responseString = new BasicResponseHandler().handleResponse(response);

                    if (response.getStatusLine().getStatusCode() == HTTP_CREATED) {
                        RoleResponse roleResponse = gson.fromJson(responseString, RoleResponse.class);

                        
                    }

                    client.close();

                } catch (IOException e) {
                    log.error("Error when sending request", e);
                }
            }
        }
    }


    private Pair<String, String> addUniqueUser() {
        Gson gson = new Gson();

        SingleUser singleUser = new SingleUser();

        String name = getUniqueName();
        singleUser.setName(new SingleUser.Names(name, name));
        singleUser.setUserName("PRIMARY/" + name);
        singleUser.setPassword(name);

        String json  = gson.toJson(singleUser);

        CloseableHttpClient client = getHttpClient();

        if (client != null) {

            HttpPost httpPost = new HttpPost(SCIM_USERS_URL);

            try {
                setHeadersAndBody(json, httpPost);

                CloseableHttpResponse response = client.execute(httpPost);
                String responseString = new BasicResponseHandler().handleResponse(response);

                if (response.getStatusLine().getStatusCode() == HTTP_CREATED) {
                    RoleResponse roleResponse = gson.fromJson(responseString, RoleResponse.class);

                    return new Pair<>(name, roleResponse.getId());
                }

                client.close();

            } catch (IOException e) {
                log.error("Error when sending request", e);
            }
        }

        return null;
    }


    private Pair<String, String> addUniqueRole() {
        Gson gson = new Gson();

        SingleRole singleRole = new SingleRole();
        String name = getUniqueName();
        singleRole.setDisplayName("PRIMARY/" + name);

        String json = gson.toJson(singleRole);

        CloseableHttpClient client = getHttpClient();

        if (client != null) {

            HttpPost httpPost = new HttpPost(SCIM_GROUPS_URL);

            try {
                setHeadersAndBody(json, httpPost);

                CloseableHttpResponse response = client.execute(httpPost);
                String responseString = new BasicResponseHandler().handleResponse(response);

                if (response.getStatusLine().getStatusCode() == HTTP_CREATED) {
                    RoleResponse roleResponse = gson.fromJson(responseString, RoleResponse.class);

                    return new Pair<>(name, roleResponse.getId());
                }

                client.close();

            } catch (IOException e) {
                log.error("Error when sending request", e);
            }
        }

        return null;
    }


    private String getUniqueName() {
        return UUID.randomUUID().toString().substring(15);
    }

    private CloseableHttpClient getHttpClient() {
        CloseableHttpClient client = null;

        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    builder.build());

            client = HttpClients.custom().setSSLSocketFactory(
                    sslsf).build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            log.error("Error when setting up SSL", e);
        }

        return client;
    }

    private void setHeadersAndBody(String json, HttpEntityEnclosingRequestBase httpMethod) {
        try {
            StringEntity entity = new StringEntity(json);
            httpMethod.setEntity(entity);
            httpMethod.setHeader("Accept", "application/json");
            httpMethod.setHeader("Content-type", "application/json");

            httpMethod.setHeader("Authorization", "Basic " + Base64.getEncoder().
                    encodeToString("admin:admin".getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            log.error("Encoding exception when populating request", e);
        }
    }

}
