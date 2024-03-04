import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent {

  constructor(private cookieService:CookieService){
    this.cookieService.delete('userId', '/', 'localhost');
  }

  readFormData(formData:any){
    const recipient = 'sheshanksharma2903@gmail.com,yashdubey415@gmail.com';
    const subject = encodeURIComponent('Feedback of CareAssist from ' + formData.form.value.name);
    const body = encodeURIComponent(formData.form.value.message);

    const mailtoLink = `https://mail.google.com/mail/?view=cm&fs=1&to=${recipient}&su=${subject}&body=${body}`;
    window.open(mailtoLink, '_blank');
  }
}
