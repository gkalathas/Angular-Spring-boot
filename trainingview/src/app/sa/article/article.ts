export class Article {
  votes: number;
  title: string;
  link: string;


  constructor(votes: number, title: string, link: string) {
    this.votes = votes;
    this.title = title;
    this.link = link;
  }

  voteUp() {
    this.votes += 1;
  }

  voteDown() {
    this.votes -= 1;
  }

  domain(): string {
    try {
      const domainAndPath: string = this.link.split('//')[1];

      return domainAndPath.split('/')[0];
    } catch (err) {
      return null;
    }
  }
}
