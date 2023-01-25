import {Component, Input, OnChanges, OnInit, ViewChild} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {ActivatedRoute, Router} from '@angular/router';
import {FormGroup, NgForm} from '@angular/forms';
import {Campaign} from '../../campaign';
import {TranslateService} from '@ngx-translate/core';
import {CampaignService} from '../../campaign.service';
import {CampaignTypeService} from '../../campaign-type.service';

@Component({
  selector: 'app-campaign-view',
  templateUrl: './campaign-view.component.html',
  styleUrls: ['./campaign-view.component.scss']
})
export class CampaignViewComponent implements OnInit, OnChanges {

  viewType: string;
  @Input()selectedCampaign: Campaign;

  @ViewChild('f') form: FormGroup;
  retrievedId = null;
  campaign: Campaign = new Campaign();


  constructor(private messageService: MessageService,
              private route: ActivatedRoute,
              private router: Router,
              private confirmationService: ConfirmationService,
              private translateService: TranslateService,
              private campaignService: CampaignService,
              private campaignTypeService: CampaignTypeService) { }



  ngOnInit(): void {
  //   this.retrievedId = +this.route.snapshot.params['id'];
  //   console.log(this.retrievedId);
  //
  //   if (!this.retrievedId) {
  //     this.form = this.campaignService.initializeCampaignForm();
  //   }
  //   else{
  //     this.campaignService.getCampaign(this.retrievedId).subscribe(
  //       response => {
  //         this.form = this.campaignService.initializeCampaignForm(response);
  //       });
  //   }
  }

  ngOnChanges(): void {
    if (this.selectedCampaign) {
      this.viewType = 'edit';
      this.form.patchValue(this.selectedCampaign);
    }else {
      this.viewType = 'add';
      this.form.reset();
    }
  }



  onSubmit() {
    this.campaign.id = this.form.value.id;
    console.log(this.form);
    console.log(this.campaign.id);
  }

  onAddCampaign() {

  }
}
