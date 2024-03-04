import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  openTab(tabId: string): void {
    const tabContents: NodeListOf<Element> = document.querySelectorAll('.home-content');
    tabContents.forEach((content: Element) => {
      content.classList.remove('active');
    });

    const tab: NodeListOf<Element> = document.querySelectorAll('.tab');
    tab.forEach((content: Element) => {
      content.classList.remove('active');
    });

    const selectedTabContent: Element | null = document.getElementById(tabId);
    if (selectedTabContent) {
      selectedTabContent.classList.add('active');
    }

    const selectedTab: Element | null = document.getElementById("button-" + tabId);
    if (selectedTab) {
      selectedTab.classList.add('active');
    }
  }

  setSelectedTabActive(tabId: string) {

    const tab: NodeListOf<Element> = document.querySelectorAll('.tab');
    tab.forEach((content: Element) => {
      content.classList.remove('active');
    });

    const selectedTab: Element | null = document.getElementById("button-" + tabId);
    if (selectedTab) {
      selectedTab.classList.add('active');
    }
  }

}

