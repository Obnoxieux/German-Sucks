//
//  ShareCard.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 08.06.23.
//

import SwiftUI
import shared

struct ShareCard: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 6.0) {
            Image(systemName: "bubble.left.fill")
                .font(.title2)
                .foregroundColor(Color.germanSecondary)
            Text(Constants().getExplanationText())
        }
        .padding()
        .background(Color.secondaryBackground)
        .cornerRadius(15)
    }
}

struct ShareCard_Previews: PreviewProvider {
    static var previews: some View {
        ShareCard()
    }
}
