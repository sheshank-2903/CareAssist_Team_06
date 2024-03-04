import { Component } from '@angular/core';
import { Route, Router, RouterConfigOptions } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  constructor(private cookieService: CookieService, private router: Router) { }

  logout() {
    this.cookieService.delete('userId', '/', 'localhost');
    this.router.navigate(['/homePage']);
  }

  openTab(tabId: string): void {
  
    const tab: NodeListOf<Element> = document.querySelectorAll('.tab');
    tab.forEach((content: Element) => {
      content.classList.remove('active');
    });

    const selectedTab: Element | null = document.getElementById("btn-" + tabId);
    if (selectedTab) {
      selectedTab.classList.add('active');
    }
  }
}
