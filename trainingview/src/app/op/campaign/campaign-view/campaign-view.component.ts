import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ConfirmationService, MessageService, SelectItem} from 'primeng/api';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {Campaign} from '../../campaign';
import {TranslateService} from '@ngx-translate/core';
import {CampaignService} from '../../campaign.service';
import {CampaignTypeService} from '../../campaign-type.service';
import {ToitsuToasterService} from '../../../toitsu-shared/toitsu-toaster/toitsu-toaster.service';
import {ToitsuBlockUiService} from '../../../toitsu-shared/toitsu-blockui/toitsu-block-ui.service';
import {Calendar} from 'primeng/calendar';
import {campaignConsts} from '../../campaign.consts';
import {CampaignType} from "../../campaign-type";

@Component({
  selector: 'app-campaign-view',
  templateUrl: './campaign-view.component.html',
  styleUrls: ['./campaign-view.component.scss']
})
export class CampaignViewComponent implements OnInit {

  @Input()selectedCampaign: Campaign;

  @ViewChild('f') form: NgForm;
  campaign: Campaign = new Campaign();
  campaign1: any;

  retrievedId: number;

  selectType: any;
  campaignTypes: Array<SelectItem>;
  // campaignTypes: any =  this.campaignTypeService.getAll().subscribe(
  //   response => {
  //
  //   }
  // );
  statuses: Array<SelectItem>;
  
  @ViewChild(Calendar) startDate; 

  viewType: string;


  constructor(private messageService: MessageService,
              private route: ActivatedRoute,
              private router: Router,
              private confirmationService: ConfirmationService,
              private translateService: TranslateService,
              private campaignService: CampaignService,
              private campaignTypeService: CampaignTypeService,
              private toitsuToasterService: ToitsuToasterService,
              private toitsuBlockUiService: ToitsuBlockUiService) { }



  ngOnInit(): void {
    this.retrievedId = +this.route.snapshot.params['id'];

    if (!this.retrievedId) {
     this.viewType = 'add';
     this.campaign = new Campaign();
    }
    else{
      this.viewType = 'edit';
      this.campaign1 = this.route.snapshot.data;

    }
  }


  onSubmit() {
    this.campaign.id = this.form.value.id;
    // console.log(this.form);
    // console.log(this.campaign.id);
  }

  onAddCampaign() {
    this.router.navigate(['']);
  }

  saveCampaign() {
    this.toitsuToasterService.clearMessages();
    this.toitsuBlockUiService.blockUi();

    console.log(this.campaign);
    this.campaignService.saveCampaign(this.campaign).subscribe(
      response => {
        this.toitsuToasterService.showSuccessStay();
        this.router.navigate(['/op/campaign/view', response['id']]);
      },
      responseError => {
        this.toitsuToasterService.apiValidationErrors(responseError);
      }
    ).add(() => {
      this.toitsuBlockUiService.unblockUi();
    });
  }

  deleteCampaign() {
    this.confirmationService.confirm({
      message: this.translateService.instant('global.delete.confirmation'),
      accept: () => {
        this.toitsuToasterService.clearMessages();
        this.toitsuBlockUiService.blockUi();
        console.log(this.retrievedId + 'inside delete');
        this.campaignService.deleteCampaign(this.retrievedId).subscribe(
          response => {
            this.toitsuToasterService.showSuccessStay(this.translateService.instant('global.delete.success'));
            this.router.navigate(['/op/campaign/list']);
          },
          responseError => {
            this.toitsuToasterService.showErrorStay(responseError);
          }
        ).add(() => {
          this.toitsuBlockUiService.unblockUi();
        });
      }
    });
  }

  homePage() {
    this.router.navigate(['']);
  }

  onUpdateProduct() {

  }

  dropType: string = '';
  campaignTypeIdChange($event: any) {

  }
  getSelectedCampaign() {
    this.campaignTypeService.getall().subscribe(
      campaignT => this.selectType = campaignT
    );
  }
}
