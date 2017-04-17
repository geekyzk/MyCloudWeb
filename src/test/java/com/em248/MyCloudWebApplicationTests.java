package com.em248;

import com.em248.config.cloudfoundry.CloudFoundryConfig;
import com.em248.config.cloudfoundry.UserClient;
import org.cloudfoundry.client.v2.applications.ListApplicationsRequest;
import org.cloudfoundry.client.v2.applications.ListApplicationsResponse;
import org.cloudfoundry.operations.applications.ApplicationSummary;
import org.cloudfoundry.operations.organizations.OrganizationSummary;
import org.cloudfoundry.operations.spaces.SpaceSummary;
import org.cloudfoundry.uaa.users.CreateUserRequest;
import org.cloudfoundry.uaa.users.Email;
import org.cloudfoundry.uaa.users.Name;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyCloudWebApplicationTests {


	@Resource
	private UserClient userClient;
	@Resource
    private CloudFoundryConfig cloudFoundryConfig;

	@Before
	public void contextLoads() {
		userClient.init("tian@cai.com","tian@cai.com");
	}

	@Test
	public void org(){
		OrganizationSummary organizationSummary = userClient.getCloudFoundryOperations().organizations().list().blockFirst();
		System.out.println(organizationSummary.toString());
	}

	@Test
	public void apps(){
//		Flux<SpaceSummary> list = userClient.getCloudFoundryOperations().spaces().list();
//		System.out.println(list.collectList().block().size());
		ListApplicationsResponse block = userClient.getCloudFoundryClient().applicationsV2().list(ListApplicationsRequest
				.builder()
				.build()).block();
		System.out.println(block);
	}

	@Test
    public void user(){
	    userClient.getUaaClient().users().create(CreateUserRequest.builder()
        .userName("tian@jkl.com")
        .active(true)
        .email(Email.builder()
        .value("tian@jkl.com")
                .primary(true)
        .build())
        .name(Name.builder().familyName("tian").givenName("wucai").build())
        .verified(true)
        .password("123456")
        .build()).block();
    }

    @Test
    public void testConfig(){
        System.out.println(cloudFoundryConfig.getHost());
    }
}
