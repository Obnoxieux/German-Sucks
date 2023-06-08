//
//  MainWordView.swift
//  German Sucks iOS
//
//  Created by David Battefeld on 08.06.23.
//

import SwiftUI
import shared

struct MainWordView: View {
    var body: some View {
        VStack(spacing: 15) {
            ExplanationCard()
            MainWordCard()
            ShareCard()
            Spacer()
            Button(action: {print("tapped")}) {
                Label("Load your new German word here", systemImage: "sparkles")
            }
            .buttonStyle(.borderedProminent)
            .controlSize(.large)
            .padding(.bottom)
        }
        .padding()
        .navigationTitle("German Sucks")
    }
}

struct MainWordView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            MainWordView()
        }
    }
}
