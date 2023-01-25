import {Component, OnInit, ViewChild} from '@angular/core';
import {CampaignModel} from '../../campaign.model';
import {Router} from '@angular/router';
import {CampaignService} from '../campaign-service';
import {MessageService} from 'primeng/api';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-add-campaign',
  templateUrl: './add-campaign.component.html',
  styleUrls: ['./add-campaign.component.scss']
})
export class AddCampaignComponent implements OnInit {

  campaign: CampaignModel;

  campaignId: number;
  campaignName: string;
  campaignDesc: string;
  campaignType: string;
  campaignCost: number;
  startDate: Date;
  endDate: Date;

  @ViewChild('f') signForm: NgForm;
  campaignTypes: string[];

  constructor(private router: Router, private messageService: MessageService, private campaignService: CampaignService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    // console.log(form);

    console.log(this.signForm);

  }


  homePage() {
    this.router.navigate(['']);
  }


  showAll() {
    this.router.navigate(['/gp/campaigns/showAll']);
  }
  onAddCampaign() {
    this.campaign = new CampaignModel(
      this.campaignId,
      this.campaignName,
      this.campaignDesc,
      this.campaignType,
      this.campaignCost,
      this.startDate,
      this.endDate);
    this.campaignService.addCampaign(this.campaign);

    this.campaignService.createCampaign(this.campaign).subscribe(
      response => {
        console.log(response);
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Product Added'});
      },
      error => {
        console.log('error occurred');
        this.messageService.add({severity: 'error', summary: 'Error', detail: error});
      }
    );
  }
}
