//
//  ShareCard.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 08.06.23.
//

import SwiftUI
import shared

struct ShareCard: View {
    @ObservedObject var vm: MainWordView.MainWordViewModel
    
    var body: some View {
        VStack(alignment: .leading, spacing: 15.0) {
            Image(systemName: "bubble.left.fill")
                .font(.title2)
                .foregroundColor(Color.germanSecondary)
            Text("Let the world know!")
            HStack {
                ShareLink(item: vm.shareLookupDataProvider.getShareText(word: vm.currentWord)) {
                    Text("Share with text")
                        .padding(9)
                        .foregroundColor(.white)
                        .background(Color.germanSecondary)
                        .cornerRadius(7)
                }
                Spacer()
                ShareLink("Share without text", item: vm.currentWord)
            }
            .tint(.germanSecondary)
            .labelStyle(.titleOnly)
        }
        .padding()
        .background(Color.secondaryBackground)
        .cornerRadius(15)
    }
}

struct ShareCard_Previews: PreviewProvider {
    static var previews: some View {
        ShareCard(vm: MainWordView.MainWordViewModel())
    }
}
