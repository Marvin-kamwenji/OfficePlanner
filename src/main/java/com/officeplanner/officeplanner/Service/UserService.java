package com.officeplanner.officeplanner.Service;

import com.officeplanner.officeplanner.Dao.RoleRepository;
import com.officeplanner.officeplanner.Dao.UserRepository;
import com.officeplanner.officeplanner.Model.Role;
import com.officeplanner.officeplanner.Model.User;
import net.bytebuddy.utility.RandomString;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

 /*---------------------------------------------------------------------------------------------------*/
 //DECLARING VARIABLES
 //LIMIT TRIALS TO WRONG PASSWORDS
 /*--------------------------------------------------------------------------------------------------*/
 public static final int MAX_FAILED_ATTEMPTS = 3;
 private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; //24 hours
/*----------------------------------------------------------------------------------------------------*/
    //AUTOWIRING
/*----------------------------------------------------------------------------------------------------*/
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JavaMailSender mailSender;

 /*----------------------------------------------------------------------------------------------------------*/
    //CRUD PROCESSES
 /*----------------------------------------------------------------------------------------------------------*/
 /*..................................FOR NEW REGISTERED USERS-----------------------------------------------*/
    //CREATE
    public void saveUser(User user)
    {
        userRepository.save(user);

    }

    //RETRIEVE
    public List<User> listAll()
    {
        System.out.println(userRepository.findAll());
        return userRepository.findAll();

    }

    //DELETE
    public void deleteUser(Integer user_id)
    {
        userRepository.deleteById(user_id);

    }

    //UPDATE
    public User editedUser(Integer user_id)
    {
        return userRepository.findById(user_id).get();

    }

 /*---------------------------------------------------------------------------------------------------------*/
    //FOR MOVING NEW REGISTERED USERS TO CURRENT USERS
 /*---------------------------------------------------------------------------------------------------------*/

    public List<User> listCurrentUsers()
    {
        System.out.println(userRepository.findAll());
        return userRepository.findAll();

    }

  /*---------------------------------------------------------------------------------------------------------*/
  //RESETTING PASSWORD
  //FORGOT PASSWORD
  /*---------------------------------------------------------------------------------------------------------*/

    public void updateResetPasswordToken(String token, String email) throws UserPrincipalNotFoundException{
        User user = userRepository.findByEmail(email);
        if(user != null){
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }
        else{
            throw new UserPrincipalNotFoundException("Could not find any user with the email");
        }
    }

    public User getByResetPasswordToken(String token){
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    //IMPLEMENTING SEARCH IN THE TABLES
    /*--------------------------------------------------------------------------------------------------------------*/
    public List<User> listAll(String keyword){
        if (keyword != null){
            return userRepository.search(keyword);
        }
        return userRepository.findAll();
    }

    /*-------------------------------------------------------------------------------------------------------*/
    // LIST OF ROLES
    //GIVING USER DEFAULT ROLES
    /*-------------------------------------------------------------------------------------------------------*/

    public List<Role> listRoles(){
        return roleRepository.findAll();
    }

    public void registerDefaultUser(User user){
        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);

        userRepository.save(user);
    }

    /*--------------------------------------------------------------------------------------------------*/
    //LIMITING PASSWORD TRIAL ATTEMPTS
    /*---------------------------------------------------------------------------------------------------*/
    //UPDATES THE NUMBER OF FAILED ATTEMPTS OF A USER
    public void increasedFailedAttempts(User user){
        int newFailAttempts = user.getFailedAttempt() + 1;
        userRepository.updateFailedAttempts(newFailAttempts, user.getEmail());
    }

    //WHEN USER LOGS IN SUCCESSFULLY FAILED ATTEMPTS IS RESET TO ZERO
    public void resetFailedAttempts(String email){
        userRepository.updateFailedAttempts(0, email);
    }

    //LOCKS USER ACCOUNT IF THE NUMBER OF FAILED ATTEMPTS REACH THE MAXIMUM
    public void lock(User user){
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        userRepository.save(user);
    }

    //WHEN LOCK DURATION EXPIRES ALLOWS USER TO LOGIN AS USUAL
    public boolean unlockWhenTimeExpired(User user){
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if(lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis){
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);

            userRepository.save(user);
            return true;
        }
        return false;
    }

    /*--------------------------------------------------------------------------------------------------*/
    //REGISTRATION OF A NEW USER WITH EMAIL VERIFICATION
    /*-------------------------------------------------------------------------------------------------*/

   public void register(User user, String siteURL)
   throws UnsupportedEncodingException, MessagingException {
       String encodedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(encodedPassword);

       String randomCode = RandomString.make(64);
       user.setVerificationCode(randomCode);
       user.setEnabled(false);

       userRepository.save(user);

       sendVerificationEmail(user, siteURL);
   }

   private void sendVerificationEmail(User user, String siteURL)
   throws MessagingException, UnsupportedEncodingException{
       String toAddress = user.getEmail();
       String fromAddress = "meetingofficeplannerr@gmail.com";
       String senderName = "Meeting Office Planner ";
       String subject = "Kindly verify your registration";
       String content = "Welcome [[name]], <br>"
               +"Thanks for joining meeting office planner, the largest community" +
               "of meeting planners!! Click below to verify your email address and activate " +
               "your account: <br>"
               +"<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
               +"if you didn't request this code, you can safely ignore this email."
               +"Someone else might have typed your email address by mistake"
               +"Thank you, <br>"
               +"Meeting Office Planner.";

       MimeMessage message = mailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(message);

       helper.setFrom(fromAddress, senderName);
       helper.setTo(toAddress);
       helper.setSubject(subject);

       content = content.replace("[[name]]", user.getFullName());
       String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

       content = content.replace("[[URL]]", verifyURL);

       helper.setText(content, true);

       mailSender.send(message);

       System.out.println("Email has been sent");

   }

   public boolean verify(String verificationCode){
       User user = userRepository.findByVerificationCode(verificationCode);

       if (user == null || user.isEnabled()){
           return false;
       } else {
           user.setVerificationCode(null);
           user.setEnabled(true);
           userRepository.save(user);

           return true;
       }
   }
}
