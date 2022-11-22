package com.acme.gym4u.test.step;

import com.acme.gym4u.profile.domain.persistence.ProfileRepository;
import com.acme.gym4u.profile.domain.service.ProfileService;
import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.profile.service.ProfileServiceImpl;
import com.acme.gym4u.profile.resource.ProfileResource;
import com.acme.gym4u.security.service.UserServiceImpl;
import org.junit.Before;
import org.mockito.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;



public class UserStepDefinition {
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private ProfileService profileService;
    @Mock
    private Validator validator;

    @InjectMocks
    @Spy
    private UserServiceImpl userService;


    private List<Profile> profileList;

    private Profile profile;

    private List<Profile> results;

    private Profile result;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        profile=new Profile();
        profile.setId(1L);
    }

    @Given("I am Admin User")
    public void imUser(){

    }


    @And("Exist the following Profiles in the repository")
    public void existTheFollowingProfilesInTheRepository(){

    }

    @When("I get all Profiles")
    public void iGetAllProfiles(){

    }

    @Then("I should get a list of Profiles with length {long}")
    public void iShouldGetAListOfProfilesWithLength(){

    }






}
