import java.util.List;
import java.util.Stack;

public class Auction  {
	private double minimumBid;
	private boolean hasImmediateBuying; //indicates whether the Auction has a price that would immediately by the object
	private double buyNowPrice; //Price set by Auctioneer that would immediately buy the object without entering the auction
	private double biddingDuration; //Duration of time for bidding, maximum is 48 hours
	private double startingPrice;
	private double auctionFee;
	private int maximumDurationHours; //represents 48 hours
	private int sendToLouis; //1/1000 chance of being true, activates sendDirectToLouisWallet
	private boolean auctionReadyToBegin; //flag that signals when everything is valid to begin auction, default is false
	private List interactions; //documents all people interacting with auction, this is both the auctioneer and all bidders. When Auction object is created, the account that created it(the auctioneer) is stored here
	private double finalPrice;
	private Stack <Double> currentPrice;
	private Stack <String> iD;
	private Account user;
	
	public Auction(double minimumBid, boolean hasImmediateBuying, double buyNowPrice, double biddingDuration, double startingPrice, double auctionFee, int maxiumDurationHours, int sendToLouis, boolean auctionReadyToBegin, List interations, double finalPrice, Stack <Double> currentPrice, Stack <String> iD, String username, String userID) {
		this.minimumBid = minimumBid;
		this.hasImmediateBuying = hasImmediateBuying;
		this.buyNowPrice = buyNowPrice;
		this.biddingDuration = biddingDuration;
		this.startingPrice = startingPrice;
		this.auctionFee = auctionFee;
		this.maximumDurationHours = maximumDurationHours;
		this.sendToLouis = sendToLouis;
		this.auctionReadyToBegin = auctionReadyToBegin;
		this.interactions = interactions;
		this.finalPrice = finalPrice;
		this.currentPrice = currentPrice;
		this.iD = iD;
		user = new Account(username, userID);
	}
	
	public void setUpAuction(double time, double minBid, boolean hasBuyNow, double buyNow) { //add validateNFT
		biddingDuration = time;
		minimumBid = minBid;
		hasImmediateBuying = hasBuyNow;
		buyNowPrice = buyNow;
	}
	
	public void prepareAuction() throws Exception {
		if (biddingDuration <= 48 && minimumBid > 0 && startingPrice > 0){ //check validateNFT
			auctionReadyToBegin = true;
		}
		else {
			throw new Exception ("At least one part of your auction is incorrect.");
		}
	}
	
	public void calculateAuctionFee() {
		auctionFee = biddingDuration / finalPrice;
	}
	//Split into two methods
	public void storeBids(double bid) throws Exception {
		if (bid >= buyNowPrice) {
			biddingDuration = 0;
			currentPrice.push(bid);
			finalPrice = currentPrice.peek();
			iD.push("Username: " + user.getUsername() + "\n User ID: " + user.getUserID());
		}
		else {
			if (bid <= currentPrice.peek()) {
				throw new Exception ("Bid is too low");
			}
			else {
				currentPrice.push(bid);
				finalPrice = currentPrice.peek();
				iD.push("Username: " + user.getUsername() + "\n User ID: " + user.getUserID());
			}
		}
		if (biddingDuration == 0 && finalPrice < minimumBid) {
			finalPrice = (Double) null;
		}
	}
	
	public boolean getAuctionReadytoBegin() {
		return auctionReadyToBegin;
	}
	
	public void sendNFTToBidder() {
		//call native transfer;
	}
	
	public void sendDirectToLWallet (double amount) {
		//call native transfer;
	}
}
