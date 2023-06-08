//
//  ExplanationCard.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 08.06.23.
//

import SwiftUI
import shared

struct ExplanationCard: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 6.0) {
            Image(systemName: "lightbulb.fill")
                .font(.title2)
                .foregroundColor(Color.germanTertiary)
            Text(Constants().getExplanationText())
        }
        .padding()
        .background(Color.secondaryBackground)
        .cornerRadius(15)
    }
}

struct ExplanationCard_Previews: PreviewProvider {
    static var previews: some View {
        ExplanationCard()
    }
}
