import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-health-care-provider',
  templateUrl: './health-care-provider.component.html',
  styleUrls: ['./health-care-provider.component.css']
})
export class HealthCareProviderComponent {
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