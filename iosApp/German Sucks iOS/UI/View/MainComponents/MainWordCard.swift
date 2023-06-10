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
        VStack(alignment: .leading, spacing: 8.0) {
            HStack {
                Image(systemName: "text.book.closed.fill")
                    .font(.title2)
                    .foregroundColor(Color.germanPrimary)
                Spacer()
                Image(systemName: "questionmark.diamond.fill")
                    .font(.title2)
                    .foregroundColor(Color.germanPrimary)
            }
            Text("Your word is...")
            Text("superkallifragilistischexpialligetisch")
                .font(.title)
                .lineLimit(1)
            HStack {
                Button("DeepL") {
                    
                }
                .buttonStyle(.borderedProminent)
                Spacer()
                Button("Google Translate") {
                    
                }
                .buttonStyle(.borderedProminent)
                Spacer()
                Button("Copy") {
                    
                }
                .buttonStyle(.bordered)
            }
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
