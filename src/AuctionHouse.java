import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class AuctionHouse {
	private HashMap pendingAuctions;
	private HashMap allCurrentAuctions;
	private HashMap pastAuctions;
	private int key = 0;
	
	
	public AuctionHouse (HashMap pendingAuctions, HashMap allCurrentAuctions, HashMap pastAuctions) {
		this.pendingAuctions = pendingAuctions;
		this.allCurrentAuctions = allCurrentAuctions;
		this.pastAuctions = pastAuctions;
	}
	
	public void createAuction (double minimumBid, boolean hasImmediateBuying, double buyNowPrice, double biddingDuration, double startingPrice, double auctionFee, int maxiumDurationHours, int sendToLouis, boolean auctionReadyToBegin, List interations, double finalPrice, Stack <Double> currentPrice, Stack <String> iD, String username, String userID) {
		Auction auction = new Auction(minimumBid, hasImmediateBuying, buyNowPrice, biddingDuration, startingPrice, auctionFee, maxiumDurationHours, sendToLouis, auctionReadyToBegin, interations, finalPrice, currentPrice, iD, username, userID);
		pendingAuctions.put(key, auction);
	}
	
	public void beginAuction() {
		if (((Auction) pendingAuctions.get(key)).getAuctionReadytoBegin() == true) {
			allCurrentAuctions.put(key, pendingAuctions.get(key));
		}
	}
	
}
