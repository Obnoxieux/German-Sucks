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
        VStack(alignment: .leading, spacing: 15.0) {
            Image(systemName: "bubble.left.fill")
                .font(.title2)
                .foregroundColor(Color.germanSecondary)
            Text("Let the world know!")
            HStack {
                Button("Share with text") {
                    
                }
                .buttonStyle(.borderedProminent)
                .tint(.germanSecondary)
                Spacer()
                Button("Share without text") {
                    
                }
                .buttonStyle(.bordered)
                .foregroundColor(.germanSecondary)
            }
            
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
