
import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { JwtServiceService } from "../services/LoginServices/jwt-service.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private jwtService: JwtServiceService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRole = route.data['expectedRole'];
    const userRole = this.jwtService.getUserRole();
    if (userRole === expectedRole) {
      return true;
    } else {
      this.router.navigate(['/homePage']); 
      return false;
    }
  }
}