import {Component, ContentChild, OnInit, ViewChild} from '@angular/core';
import {ToitsuTableComponent} from '../../../toitsu-shared/toitsu-table/toitsu-table.component';
import {TranslateService} from '@ngx-translate/core';
import {DialogService} from 'primeng/dynamicdialog';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {Campaign} from '../../campaign';
import {CampaignService} from '../../campaign.service';
import {CampaignTypeService} from '../../campaign-type.service';

@Component({
  selector: 'app-campaign-list',
  templateUrl: './campaign-list.component.html',
  styleUrls: ['./campaign-list.component.scss']
})
export class CampaignListComponent implements OnInit {

  selectedCampaign: Campaign;

  subscriptions: Subscription[] = [];

  subscription: Subscription;

  displayAddModal: boolean = false;

  id: number;

  constructor(private translateService: TranslateService,
              private dialogService: DialogService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private campaignService: CampaignService,
              private campaignTypeService: CampaignTypeService) { }

  @ViewChild(ToitsuTableComponent) table;


  url = '/trn/campaigns/index';
  args = this.initializeArgs();

  viewLink = '/trn/campaign/view/:id';

  cols = [
    {
      field: 'extraActions',
      header: this.translateService.instant('extraActions'),
      sortField: '',
      width: 20,
      align: 'center'
    },
    {
      field: 'id',
      header: this.translateService.instant('id'),
      sortField: 'id',
      width: 20,
      align: 'center'
    },
    {
      field: 'name',
      header: this.translateService.instant('name'),
      sortField: 'name',
      width: 20,
      align: 'center'
    },
    {
      field: 'campaignTypeId',
      header: this.translateService.instant('campaignTypeId'),
      sortField: 'campaignTypeId',
      width: 20,
      align: 'center'
    },
    {
      field: 'cost',
      header: this.translateService.instant('cost'),
      sortField: 'cost',
      width: 20,
      align: 'center'
    },
    {
      field: 'isRunning',
      header: this.translateService.instant('isRunning'),
      sortField: 'isRunning',
      width: 20,
      align: 'center'
    },
    {
      field: 'startDate',
      header: this.translateService.instant('startDate'),
      sortField: 'startDate',
      width: 20,
      align: 'center'
    },
    {
      field: 'endDate',
      header: this.translateService.instant('endDate'),
      sortField: 'endDate',
      width: 20,
      align: 'center'
    },
    {
      field: 'comments',
      header: this.translateService.instant('comments'),
      sortField: 'comments',
      width: 20,
      align: 'center'
    },
  ];

  ngOnInit(): void {
  }

  loadTableData() {
    this.table.loadTableDate();
  }

  loadComplete(responseData) {
    console.log(responseData);
  }

  loadError(responseError) {
    console.log(responseError);
  }
  clearArgs() {
    this.args = this.initializeArgs();
  }

  initializeArgs() {
    return {
      name: null,
      campaignTypeId: null,
      isRunning: null,
      startDateAfter: null,
      startDateBefore: null
    };
  }

  shoAddModal() {
    this.displayAddModal = true;
    this.selectedCampaign = null;
  }


  hideAddModal(isClosed: boolean) {
    this.displayAddModal = !isClosed;
  }

  viewModal(campaign: Campaign) {
    this.displayAddModal = true;
    this.selectedCampaign = campaign;
  }

  homePage() {
    this.router.navigate(['']);
  }

  rowclicked(rowData) {
    // this.viewModal(rowData);
    console.log(rowData);
    console.log(rowData.id);
    if (this.selectedCampaign) {
    }

  }


  onAddCampaign() {

  }
}
