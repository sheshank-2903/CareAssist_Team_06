import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})

export class AboutUsComponent {
  
  constructor(private cookieService:CookieService){
    this.cookieService.delete('userId', '/', 'localhost');
  }
}
