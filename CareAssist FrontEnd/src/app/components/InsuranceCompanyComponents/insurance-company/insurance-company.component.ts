import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-insurance-company',
  templateUrl: './insurance-company.component.html',
  styleUrls: ['./insurance-company.component.css']
})
export class InsuranceCompanyComponent {
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
