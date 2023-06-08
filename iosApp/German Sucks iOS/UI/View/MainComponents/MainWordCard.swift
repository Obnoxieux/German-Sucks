//
//  MainWordCard.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 08.06.23.
//

import SwiftUI
import shared

struct MainWordCard: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 6.0) {
            Image(systemName: "text.book.closed.fill")
                .font(.title2)
                .foregroundColor(Color.germanPrimary)
            Text(Constants().getExplanationText())
        }
        .padding()
        .background(Color.secondaryBackground)
        .cornerRadius(15)
    }
}

struct MainWordCard_Previews: PreviewProvider {
    static var previews: some View {
        MainWordCard()
    }
}
