package it.uniroma3.siw.authentication;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//Classe che rende lo username sempre disponibile ai template trmite getUser()

@ControllerAdvice
public class GlobalController {
  @ModelAttribute("userDetails")
  public UserDetails getUser() {
    UserDetails user = null;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    return user;
  }
  
  @ModelAttribute("role")
  public String getAuthorityAsString() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null && !authentication.getAuthorities().isEmpty()) {
          GrantedAuthority authority = authentication.getAuthorities().iterator().next();
          return authority.getAuthority();
      }
      return null;
  }
}

