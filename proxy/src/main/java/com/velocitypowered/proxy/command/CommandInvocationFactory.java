package com.velocitypowered.proxy.command;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.CommandContext;
import com.velocitypowered.api.command.CommandInvocation;
import com.velocitypowered.api.command.CommandSource;

/**
 * Creates command invocation contexts for the given {@link CommandSource}
 * and command line arguments.
 *
 * @param <I> the type of the built invocation
 */
public interface CommandInvocationFactory<I extends CommandInvocation<?>> {

  /**
   * Returns an invocation context for the given Brigadier context.
   *
   * @param context the command context
   * @return the built invocation context
   */
  I create(final CommandContext<CommandSource> context);

  /**
   * Returns an invocation context for the given command parsing results.
   *
   * @param parse the parsed command
   * @return the built invocation context
   */
  I create(final ParseResults<CommandSource> parse);
}
