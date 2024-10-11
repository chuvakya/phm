package org.zs.phm3.ftamodel;

import java.io.IOException;
import java.util.Objects;
import javax.persistence.*;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(
        name = "t_fta_model"
)
public class FtaModelEntity {

    @Transient
    @Value(value = "${fta.url-fta-validate}")
    private String urlValidate;

    @Id
    private Long projectId;

    @Column(columnDefinition = "varchar")
    private String body;

    @Column(columnDefinition = "varchar")
    private String mapping;

    public FtaModelEntity() {
    }

    public FtaModelEntity(Long projectId, String body, String mapping) {
        this.projectId = projectId;
        this.body = body;
        this.mapping = mapping;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public boolean scramValidate() throws IOException {
        boolean isValid = false;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlValidate);
        StringEntity entity = new StringEntity(this.body);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = client.execute(httpPost);
        if (response.getStatusLine().getReasonPhrase().equals("OK")) {
            isValid = true;
        }

        return isValid;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            FtaModelEntity that = (FtaModelEntity)o;
            return this.projectId.equals(that.projectId) && Objects.equals(this.body, that.body);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.projectId, this.body});
    }
}